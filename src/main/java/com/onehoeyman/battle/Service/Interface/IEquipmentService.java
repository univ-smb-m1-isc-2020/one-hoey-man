package com.onehoeyman.battle.Service.Interface;

import com.onehoeyman.battle.Entity.Equipment;

import java.util.List;

public interface IEquipmentService {

    public List<Equipment> findAll();

    public Equipment findById(Long id);


}
