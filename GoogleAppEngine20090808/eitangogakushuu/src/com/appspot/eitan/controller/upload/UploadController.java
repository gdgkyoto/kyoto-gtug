package com.appspot.eitan.controller.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.controller.upload.FileItem;
import org.slim3.util.ByteUtil;

import com.appspot.eitan.dao.UploadDao;
import com.appspot.eitan.model.Upload;
import com.appspot.eitan.model.UploadData;


public class UploadController extends Controller {

    private static final int SIZE = 9000000;

    private UploadDao dao = new UploadDao();

    @Override
    public Navigation run() {
        FileItem formFile = requestScope("formFile");
        if (formFile != null) {
            Upload upload = new Upload();
            upload.setFileName(formFile.getFileName());
            upload.setLength(formFile.getData().length);
            byte[] bytes = formFile.getData();
            byte[][] bytesArray = ByteUtil.split(bytes, SIZE);
            for (byte[] data : bytesArray) {
                UploadData uploadData = new UploadData();
                uploadData.setBytes(data);
                upload.getDataList().add(uploadData);
            }
            dao.makePersistentInTx(upload);
        }
        return redirect(basePath);
    }
}
