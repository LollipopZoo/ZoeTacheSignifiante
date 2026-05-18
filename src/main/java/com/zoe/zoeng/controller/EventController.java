package com.zoe.zoeng.controller;

import com.zoe.zoeng.model.Event;
import com.zoe.zoeng.service.EventService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EventController {

    private final EventService eventService;

    // Injecting Service
    public EventController(EventService eventService){
        this.eventService = eventService;
    }
    // Presenting Calendar Grid
    @GetMapping("/")
    public String showCalendar(@RequestParam(required = false) Integer year,
                               @RequestParam(required = false) Integer month,
                               Model model){

        // Math Logic of Grid
        LocalDate today = LocalDate.now();
        int targetYear = (year != null) ? year : today.getYear();
        int targetMonth = (month != null) ? month : today.getMonthValue();

        YearMonth yearMonth = YearMonth.of(targetYear, targetMonth);
        LocalDate firstOfMonth = yearMonth.atDay(1);

        int dayOfWeekValue = firstOfMonth.getDayOfWeek().getValue();
        int leadingBlanks = (dayOfWeekValue == 7) ? 0 : dayOfWeekValue;

        List<Integer> daysInMonth = new ArrayList<>();
        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++){
            daysInMonth.add(i);
        }

        // Calendar structural data added to index page
        model.addAttribute("currentMonthName", yearMonth.getMonth().name());
        model.addAttribute("currentMonth", targetMonth);
        model.addAttribute("currentYear", targetYear);
        model.addAttribute("leadingBlanks", new int[leadingBlanks]);
        model.addAttribute("days", daysInMonth);

        // Adding events from database to index
        model.addAttribute("events",eventService.getAllEvents());
        return "index";
    }

    @GetMapping("/addEvent")
    public String showAddEventPage(Model model){
        model.addAttribute("event", new Event());
        return "add-event";
    }

    @PostMapping("/addEvent")
    public String addEvent(@Valid @ModelAttribute("event") Event event, BindingResult result){
        if (result.hasErrors()){
            return "add-event";
        }

        eventService.addEvent(event);
        return "redirect:/";
    }

    @GetMapping("editEvent/{id}")
    public String showEditEventPage(@PathVariable int id, Model model){
        Event event = eventService.getEventById(id);

        if(event != null){
            model.addAttribute("event", event);
            return "edit-event";
        }

        return "redirect:/";
    }

    @PostMapping("/updateEvent")
    public String updateEvent(@Valid @ModelAttribute("event") Event event, BindingResult result){
        if (result.hasErrors()){
            return "edit-event";
        }

        eventService.updateEvent(event);
        return "redirect:/";
    }

    @GetMapping("/deleteEvent/{id}")
    public String deleteEvent(@PathVariable int id){
        eventService.deleteEvent(id);
        return "redirect:/";
    }
}
