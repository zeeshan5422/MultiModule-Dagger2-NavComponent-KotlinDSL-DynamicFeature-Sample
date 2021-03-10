package com.nfs.ascent.nfslogin.data.mapper

import com.nfs.ascent.core.domain.repo.ApiResponse
import com.nfs.ascent.nfslogin.data.model.SettingDTO
import com.nfs.ascent.nfslogin.data.model.SettingEntity
import javax.inject.Inject

/**
 * Mapper for transforming objects between REST and database or REST/db and domain
 *  which are Non-nullable to Non-nullable
 */
interface Mapper<I, O> {
    fun map(input: I): O
}

/**
 * Mapper for transforming objects between REST and database or REST/db and domain
 * as [List] which are Non-nullable to Non-nullable
 */
interface ListMapper<I, O> : Mapper<List<I>, List<O>>

class DTOtoEntityMapper @Inject constructor() :
    Mapper<ApiResponse<SettingDTO>, ApiResponse<SettingEntity?>> {

    /*override fun map(input: List<SettingDTO>): List<SettingEntity> {
        return input.map {
            SettingEntity(it.appName)
        }
    }*/

    override fun map(input: ApiResponse<SettingDTO>): ApiResponse<SettingEntity?> {
        if (input.isSuccessful) {
            return ApiResponse.Success(input.data?.let { SettingEntity(appName = it.appName) })
        } else {
            return ApiResponse.Failure(input.errorResponse)
        }
    }
}

/**
 * Interface for marking models used for fetching data from REST
 */
interface IDataTransferObject

/**
 * Interface for marking models used for fetching data from database
 */
interface IEntity

/**
 * Interface for marking models used for presentation and ui
 */
interface IuiItem
