package overdiary.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import overdiary.domain.Attachment;
import overdiary.service.AttachmentService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/attachments")
public class ApiAttachmentController {
    private static final Logger log = LoggerFactory.getLogger(ApiAttachmentController.class);

    @Resource(name = "attachmentService")
    AttachmentService attachmentService;


    @PostMapping("")
    public Attachment upload(@RequestParam("file") MultipartFile file) throws Exception {
        return attachmentService.saveFile(file);
    }

}