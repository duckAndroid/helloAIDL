// IHelloInterface.aidl
package com.pythoncat.aidl_libiary;

import com.pythoncat.core.model.Student;
// Declare any non-default types here with import statements

interface IHelloInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    String hello();

    Student getOne();
}
