package com.eproject.webapi.publiccontroller;

import com.eproject.data.dto.PageDto;
import com.eproject.data.dto.referencedata.ReferenceDataDto;
import com.eproject.data.dto.trip.TripDto;
import com.eproject.service.referencedata.ReferenceDataService;
import com.eproject.service.storage.StorageService;
import com.eproject.service.trip.ITripService;
import com.eproject.webapi.BaseResponse;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/public")
public class PublicController {
    @Autowired
    private ITripService _tripService;
    @Autowired
    private StorageService _storageService;
    @Autowired
    private ReferenceDataService _referenceDataService;

    @GetMapping(path = "/trip/get-trips")
    public ResponseEntity getRoutes(
            @RequestParam(name = "startDate") @DateTimeFormat(pattern = "MM-dd-yyyy") LocalDate startDate,
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
            if (res == null) {
                return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tải tệp tin."), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi tải tệp tin."), HttpStatus.BAD_REQUEST);
        }
    }

    //endregion

    //region Reference Data
    @GetMapping("/referencedata/{referenceDataType}")
    public ResponseEntity getReferenceData(@PathVariable String referenceDataType) {
        try {
            List<ReferenceDataDto> res = _referenceDataService.getReferenceDataByType(referenceDataType);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi lấy thông tin."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/referencedata/stations-by-city/{cityCode}")
    public ResponseEntity getStationReferenceDataByCityCode(@PathVariable String cityCode) {
        try {
            JSONParser parser = new JSONParser();
            List<ReferenceDataDto> queryResult = _referenceDataService.getReferenceDataByType("STATION");
            List<ReferenceDataDto> res = queryResult.stream().filter(x -> {
                JSONObject jsonObject = null;
                try {
                    jsonObject = (JSONObject) parser.parse(x.parameterData);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                return jsonObject.get("City").equals(cityCode);
            }).toList();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new BaseResponse("Lỗi xảy ra khi lấy thông tin."), HttpStatus.BAD_REQUEST);
        }
    }
    //endregion
}
