package com.teaplantation.demo.mapper;

import com.teaplantation.demo.entity.Sensor;

public interface SensorMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer sid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    int insert(Sensor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    int insertSelective(Sensor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    Sensor selectByPrimaryKey(Integer sid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Sensor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    int updateByPrimaryKeyWithBLOBs(Sensor record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sensor
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Sensor record);
}