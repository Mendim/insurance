package cn.annpeter.insurance.actions;

import org.springframework.stereotype.Controller;

import java.io.File;

/**
 * Created by annpeter on 3/15/16.
 * 所有文件上传都继承自该类
 *
 * 注意:所有上传的文件名称都要命名为fileData
 */
@Controller
public class BaseFileReqAction extends BaseJsonResAction {

    private File fileData;              //所有上传的文件都要命名为fileData
    private String fileDataFileName;
    private String fileDataContentType;
    private String message = "0";       // 0格式错误 1成功(文件路径)  2失败
    private String filePath;


    public File getFileData() {
        return fileData;
    }

    public void setFileData(File fileData) {
        this.fileData = fileData;
    }

    public String getFileDataFileName() {
        return fileDataFileName;
    }

    public void setFileDataFileName(String fileDataFileName) {
        this.fileDataFileName = fileDataFileName;
    }

    public String getFileDataContentType() {
        return fileDataContentType;
    }

    public void setFileDataContentType(String fileDataContentType) {
        this.fileDataContentType = fileDataContentType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
