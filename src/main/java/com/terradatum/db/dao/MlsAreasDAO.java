package com.terradatum.db.dao;

import com.terradatum.db.objects.MlsAreasObject;

import java.util.List;

/**
 * Created by rbellamy on 1/1/16.
 */
public interface MlsAreasDAO {
    public List<MlsAreasObject> findMlsAreasByMlsSid(Integer mlsSid);
}
