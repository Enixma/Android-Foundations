package com.enixma.nakarinj.foundations.data.realm

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmObject
import io.realm.RealmResults
import java.util.*

abstract class RealmDataStore {

    private var realm: Realm? = null

    abstract val realmConfiguration: RealmConfiguration?

    private fun beginTransaction() {
        if (realm == null || realm!!.isClosed) {
            realm = if (realmConfiguration != null) {
                Realm.getInstance(realmConfiguration!!)
            } else {
                Realm.getDefaultInstance()
            }
        }

        if (!realm!!.isInTransaction) {
            realm!!.beginTransaction()
        }
    }

    private fun commitTransaction() {
        realm!!.commitTransaction()
        realm!!.close()
    }

    fun <E : RealmObject> copyOrUpdateToRealm(`object`: E) {
        beginTransaction()
        realm!!.copyToRealmOrUpdate(`object`)
        commitTransaction()
    }

    fun <E : RealmObject> copyToRealm(`object`: E) {
        beginTransaction()
        realm!!.copyToRealm(`object`)
        commitTransaction()
    }

    fun <E : RealmObject> findFirst(realmClass: Class<E>): E {
        beginTransaction()
        return realm!!.where(realmClass).findFirst()
    }

    fun <E : RealmObject> findFirst(realmClass: Class<E>, fieldName: String, value: String): E {
        beginTransaction()
        return realm!!.where(realmClass).equalTo(fieldName, value).findFirst()
    }

    fun <E : RealmObject> findFirst(realmClass: Class<E>, fieldOne: String, valueOne: String, fieldTwo: String, valueTwo: String): E {
        beginTransaction()
        return realm!!.where(realmClass).equalTo(fieldOne, valueOne).equalTo(fieldTwo, valueTwo).findFirst()
    }

    fun <E : RealmObject> findAll(realmClass: Class<E>): RealmResults<E> {
        beginTransaction()
        return realm!!.where(realmClass).findAll()
    }

    fun <E : RealmObject> findAll(realmClass: Class<E>, fieldName: String, value: String): RealmResults<E> {
        beginTransaction()
        return realm!!.where(realmClass).equalTo(fieldName, value).findAll()
    }

    fun <E : RealmObject> findAll(realmClass: Class<E>, fieldOne: String, valueOne: String, fieldTwo: String, valueTwo: String): RealmResults<E> {
        beginTransaction()
        return realm!!.where(realmClass).equalTo(fieldOne, valueOne).equalTo(fieldTwo, valueTwo).findAll()
    }

    fun <E : RealmObject> findFirstCopy(realmClass: Class<E>): List<E> {
        val results = ArrayList<E>()
        beginTransaction()
        val realmObject = realm!!.where(realmClass).findFirst()
        if (realmObject != null) {
            val result = realm!!.copyFromRealm(realmObject)
            results.add(result)
        }
        commitTransaction()
        return results
    }

    fun <E : RealmObject> findFirstCopy(realmClass: Class<E>, fieldName: String, value: String): List<E> {
        val results = ArrayList<E>()
        beginTransaction()
        val realmObject = realm!!.where(realmClass).equalTo(fieldName, value).findFirst()
        if (realmObject != null) {
            val result = realm!!.copyFromRealm(realmObject)
            results.add(result)
        }
        commitTransaction()

        return results
    }

    fun <E : RealmObject> findFirstCopy(realmClass: Class<E>, fieldOne: String, valueOne: String, fieldTwo: String, valueTwo: String): List<E> {
        val results = ArrayList<E>()
        beginTransaction()
        val realmObject = realm!!.where(realmClass).equalTo(fieldOne, valueOne).equalTo(fieldTwo, valueTwo).findFirst()
        if (realmObject != null) {
            val result = realm!!.copyFromRealm(realmObject)
            results.add(result)
        }
        commitTransaction()

        return results
    }

    fun <E : RealmObject> findAllCopies(realmClass: Class<E>): List<E> {
        var results: List<E> = ArrayList()
        beginTransaction()
        val realmObjectList = realm!!.where(realmClass).findAll()
        if (realmObjectList != null && !realmObjectList.isEmpty()) {
            results = realm!!.copyFromRealm(realmObjectList)
        }
        commitTransaction()

        return results
    }

    fun <E : RealmObject> findAllCopies(realmClass: Class<E>, fieldName: String, value: String): List<E> {
        var results: List<E> = ArrayList()
        beginTransaction()
        val realmObjectList = realm!!.where(realmClass).equalTo(fieldName, value).findAll()
        if (realmObjectList != null && !realmObjectList.isEmpty()) {
            results = realm!!.copyFromRealm(realmObjectList)
        }
        commitTransaction()

        return results
    }

    fun <E : RealmObject> findAllCopies(realmClass: Class<E>, fieldOne: String, valueOne: String, fieldTwo: String, valueTwo: String): List<E> {
        var results: List<E> = ArrayList()
        beginTransaction()
        val realmObjectList = realm!!.where(realmClass).equalTo(fieldOne, valueOne).equalTo(fieldTwo, valueTwo).findAll()
        if (realmObjectList != null && !realmObjectList.isEmpty()) {
            results = realm!!.copyFromRealm(realmObjectList)
        }
        commitTransaction()

        return results
    }

    fun <E : RealmObject> delete(realmClass: Class<E>, fieldName: String, value: String) {
        beginTransaction()
        val result = realm!!.where(realmClass)
                .equalTo(fieldName, value)
                .findFirst()

        result?.deleteFromRealm()
        commitTransaction()
    }

    fun <E : RealmObject> delete(realmClass: Class<E>, fieldOne: String, valueOne: String, fieldTwo: String, valueTwo: String) {
        beginTransaction()
        val result = realm!!.where(realmClass)
                .equalTo(fieldOne, valueOne)
                .equalTo(fieldTwo, valueTwo)
                .findFirst()

        result?.deleteFromRealm()
        commitTransaction()
    }

    fun <E : RealmObject> deleteAll(realmClass: Class<E>) {
        beginTransaction()
        realm!!.delete(realmClass)
        commitTransaction()
    }

    fun <E : RealmObject> deleteAll(realmClass: Class<E>, fieldName: String, value: String) {
        beginTransaction()
        val results = realm!!.where(realmClass)
                .equalTo(fieldName, value)
                .findAll()
        results?.deleteAllFromRealm()
        commitTransaction()
    }

    fun <E : RealmObject> deleteAll(realmClass: Class<E>, fieldOne: String, valueOne: String, fieldTwo: String, valueTwo: String) {
        beginTransaction()
        val results = realm!!.where(realmClass)
                .equalTo(fieldOne, valueOne)
                .equalTo(fieldTwo, valueTwo)
                .findAll()

        results?.deleteAllFromRealm()
        commitTransaction()
    }

    fun clear() {
        beginTransaction()
        realm!!.deleteAll()
        commitTransaction()
    }

}