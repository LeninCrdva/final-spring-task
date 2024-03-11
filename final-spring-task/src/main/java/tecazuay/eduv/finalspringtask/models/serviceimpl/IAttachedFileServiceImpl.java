package tecazuay.eduv.finalspringtask.models.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tecazuay.eduv.finalspringtask.models.dao.IAttachedFile;
import tecazuay.eduv.finalspringtask.models.entity.Attached_file;
import tecazuay.eduv.finalspringtask.models.service.IAttachedFileService;

import java.util.List;

@Service
public class IAttachedFileServiceImpl implements IAttachedFileService {

    @Autowired
    private IAttachedFile fileDao;

    @Override
    @Transactional(readOnly = true)
    public List<Attached_file> findAll() {
        return fileDao.findAll();
    }

    @Override
    @Transactional
    public void save(Attached_file file) {
        fileDao.save(file);
    }

    @Override
    @Transactional(readOnly = true)
    public Attached_file findOne(Long id) {
        return fileDao.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Attached_file findOneByTicket(Long id){return fileDao.findOneByTicket(id);};

    @Override
    @Transactional
    public void delete(Long id) {
        fileDao.delete(id);
    }
}
