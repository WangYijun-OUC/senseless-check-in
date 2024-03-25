package org.ouc.schedule.Controller;

import lombok.extern.slf4j.Slf4j;
import org.ouc.common.pojo.dto.DateStringDto;
import org.ouc.common.pojo.entity.Liuliang;
import org.ouc.common.pojo.entity.TestLiuliang;
import org.ouc.common.result.Result;
import org.ouc.schedule.service.AccessEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/accessEvent")
@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
public class AccessEventController {

    @Autowired
    private AccessEventService accessEventService;

    @GetMapping(value = "/getTotalAccessCountByDate/{date}")
    public Result<Integer> getTotalAccessCountByDate(@PathVariable String date) {
        return accessEventService.getTotalAccessCountByDate(date);
    }


    @GetMapping(value = "/getTotalAccessCountByWeek/{date}")
    public Result<Integer> getTotalAccessCountByWeek(@PathVariable String date) {
        return accessEventService.getTotalAccessCountByWeek(date);
    }


    @GetMapping(value = "/getTotalAccessCountByMonth/{date}")
    public Result<Integer> getTotalAccessCountByMonth(@PathVariable String date) {
        return accessEventService.getTotalAccessCountByMonth(date);
    }

    @PostMapping(value = "/liuliang")
    public Map<String, Object> liuliang(@RequestBody ArrayList<String> dateStringDto) {



        int size = accessEventService.liuliang(dateStringDto.get(0), dateStringDto.get(1));
        List<Liuliang> l = accessEventService.liuliangs(dateStringDto.get(0), dateStringDto.get(1));
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("total", size); // 总人流量为员工数量
        responseData.put("children", l); // 员工列表作为children的值

        return responseData;

    }
}
