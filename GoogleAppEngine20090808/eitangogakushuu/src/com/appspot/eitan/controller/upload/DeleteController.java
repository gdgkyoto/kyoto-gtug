package com.appspot.eitan.controller.upload;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

import com.appspot.eitan.dao.UploadDao;
import com.appspot.eitan.model.Upload;


public class DeleteController extends Controller {

    private UploadDao dao = new UploadDao();

    @Override
    public Navigation run() {
        Upload upload = dao.find(key(), version());
        dao.deletePersistentInTx(upload);
        return redirect(basePath);
    }
}
