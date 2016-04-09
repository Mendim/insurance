package cn.annpeter.insurance.services;

import cn.annpeter.insurance.daos.SafeDeleteDao;
import cn.annpeter.insurance.entities.Score;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by annpeter on 4/8/16.
 */
@Service
public class ScoreService {
    @Resource
    SafeDeleteDao safeDeleteDao;

    public void save(Score score) throws Exception {
        safeDeleteDao.save(score);
    }
}
