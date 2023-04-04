package hsu.bugicare.bugicareserver.service;

import hsu.bugicare.bugicareserver.domain.UserImage;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileHandler {
    public UserImage parseFileInfo(MultipartFile imageFile) throws Exception {
        if (imageFile.isEmpty()) {
            return null;
        }

        // 파일 이름을 업로드 한 날짜로 바꾸어 저장
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 프로젝트 폴더에 저장하기 위해 절대경로 설정
        String absoluteParh = new File("").getAbsolutePath() + "//"; // 맥에서 이거 맞는지 체크,,

        String path = "images/" + current_date;
        File file = new File(path);

        if(!file.exists()) {
            file.mkdirs();
        }

        String contentType = imageFile.getContentType();
        String originalFileExtension;
        if(ObjectUtils.isEmpty(contentType)) {
            return null;
        } else {
            if (contentType.contains("image/jpeg")) {
                originalFileExtension = ".jpg";
            } else if (contentType.contains("image/png")) {
                originalFileExtension = ".png";
            } else { // 다른 확장자인 경우
                return null; // null return
            }
        }

        String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;

        return UserImage.builder()
                .original_name(imageFile.getOriginalFilename())
                .stored_path(path + "/" + new_file_name)
                .size(imageFile.getSize())
                .build();
    }
}
