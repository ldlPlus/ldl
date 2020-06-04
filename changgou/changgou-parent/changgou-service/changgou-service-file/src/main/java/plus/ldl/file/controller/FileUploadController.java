package plus.ldl.file.controller;

import org.csource.common.MyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import plus.ldl.entity.Result;
import plus.ldl.file.domain.FastDFSFile;
import plus.ldl.file.util.FastDFSUtil;

import java.io.IOException;

/**
 * @author ldl.plus
 * @date 2020年06月04日  15:16
 */
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {

    @PostMapping
    public Result upload(@RequestParam(value = "file") MultipartFile file) throws IOException, MyException {
        FastDFSFile fastDFSFile = new FastDFSFile(
                file.getOriginalFilename(),
                file.getBytes(),
                StringUtils.getFilenameExtension(file.getOriginalFilename()));
        String[] upload = FastDFSUtil.upload(fastDFSFile);

        // 拼接访问http://192.168.211.132:8080/group1/M00/00/00/wKjThF0DBzaAP23MAAXZ2mMp9oM26.jpeg

        String url = "http://192.168.31.42:8080/" + upload[0] + "/" + upload[1];
        return Result.ok().message("上传成功").data(url);
    }
}
