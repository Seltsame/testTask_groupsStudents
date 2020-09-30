package com.example.test.controller;

import com.example.test.dto.request.GroupDto;
import com.example.test.service.GroupService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController()
@RequestMapping(path = "/group")
public class GroupController {


    private final GroupService groupService;
    private final Configuration configuration;

    public GroupController(GroupService groupService, Configuration configuration) {
        this.groupService = groupService;
        this.configuration = configuration;
    }

    @PostMapping(path = "/add", produces = MediaType.TEXT_HTML_VALUE)
    public void addGroup(@RequestBody GroupDto groupDto, HttpServletResponse response) throws IOException {
        groupService.addGroup(groupDto);
        response.sendRedirect("/group/getGroups");
    }

    @GetMapping(path = "/getGroups", produces = MediaType.TEXT_HTML_VALUE)
    public void getGroups(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, HttpServletResponse response) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "creationDate"));
        /*  Map<String, Object> model = new HashMap<>();*/
        /*model.put("groupList", groupService.getGroups(pageable).getContent());*/
        /*model.put("rowPerPage", ROW_PER_PAGE);
        model.put("currentPage", pageNumber + 1);
        model.put("allPages", count / ROW_PER_PAGE + 1);
        model.put("requests", requests);
        model.put("hasPrev", hasPrev);
        model.put("prev", pageNumber - 1);
        model.put("hasNext", hasNext);
        model.put("next", pageNumber + 1);*/
        Template template = null;
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("groupList", groupService.getGroups(pageable).getContent());
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.TEXT_HTML_VALUE);
            template = configuration.getTemplate("groups.ftl");
            template.process(modelMap, response.getWriter());
        } catch (IOException | TemplateException e) {

        }
    }
}
