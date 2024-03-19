package com.eproject.webapi.publiccontroller;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.trip.RouteDto;
import com.eproject.data.dto.trip.TripDto;
import com.eproject.data.model.tripmodel.RouteEntity;
import com.eproject.service.storage.StorageService;
import com.eproject.service.trip.ITripService;
import com.eproject.service.trip.TripService;
import com.eproject.webapi.BaseResponse;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    private ITripService _tripService;

    @Autowired
    private StorageService _storageService;

    @GetMapping(path = "/trip/get-trips")
    public ResponseEntity getRoutes(
            @RequestParam(name = "startDate") LocalDate startDate,
            @RequestParam(name = "startCity") String startCity,
            @RequestParam(name = "endCity") String endCity,
            @RequestParam(name = "vehicleType", defaultValue = "", required = false) String vehicleType,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        try {
            PageDto<TripDto> result = _tripService.getTripsByLocationAndDate(startDate, startCity, endCity, vehicleType, page, size);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tìm kiếm chuyến xe."), HttpStatus.BAD_REQUEST);
        }
    }

    //region Image
    @GetMapping(
            value = "/images/{fileName}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName) {

        Resource file = _storageService.loadAsResource(fileName);

        if (file == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping(
            value = "/images/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity handleFileUpload(@RequestPart("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        try {
            UUID res = _storageService.store(file);
            if (res == null){
                return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tải tệp tin."), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tải tệp tin."), HttpStatus.BAD_REQUEST);
        }
    }

    //endregion
}
