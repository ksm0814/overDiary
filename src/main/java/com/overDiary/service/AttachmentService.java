package com.overDiary.service;

import com.overDiary.domain.Attachment;
import com.overDiary.domain.AttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AttachmentService {

    @Resource(name = "attachmentRepository")
    AttachmentRepository attachmentRepository;

    public Iterable<Attachment> findAll() {
        return attachmentRepository.findAll();
    }

    private List<Attachment> findByFileName(String oldName) {
        return attachmentRepository.findByFileName(oldName);
    }

    public Attachment saveFile(MultipartFile file) throws IOException {
        String oldName = file.getOriginalFilename();
        String dbName = oldName;

        List<Attachment> attachments = findByFileName(oldName);
        if (attachments.size() > 0) {
            dbName = renameFile(attachments, oldName);
        }
        String fileName = new String(dbName.getBytes("UTF-8"));
        String savePath = "C:\\programming\\overwatchDiary\\src\\main\\resources\\static\\images/";
        File myFile = new File(savePath + dbName);
        file.transferTo(myFile);


        Attachment attachment = new Attachment(oldName, myFile.getPath(), myFile);
        return attachmentRepository.save(attachment);
    }

    private String renameFile(List<Attachment> attachments, String oldName) {
        String[] exts = oldName.split("\\.");
        long index = 0;
        for (int i = attachments.size() - 1; i > 0; i--) {
            if (attachments.get(i).isSameName(oldName)) {
                index = attachments.size();
                break;
            }
        }
        return oldName.substring(0, oldName.length() - 4) + "(" + Long.toString(index) + ")." + exts[exts.length - 1];
    }
}
