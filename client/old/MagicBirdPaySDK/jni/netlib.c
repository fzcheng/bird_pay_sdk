/*
 * netlib.c
 *
 *  Created on: 2013-6-8
 *      Author: terry
 */
#include "netlib.h"
#include <string.h>
#include <stdio.h>
#include <assert.h>

//http://sdk.leyogame.cn/api
int url[] = { 'h' + 1, 't' + 1, 't' + 1, 'p' + 1, ':' + 1, '/' + 1, '/' + 1, 's'
		+ 1, 'd' + 1, 'k' + 1, '.' + 1, 'l' + 1, 'e' + 1, 'y' + 1, 'o' + 1, 'g' + 1, 'a' + 1, 'm'
		+ 1, 'e' + 1, '.' + 1, 'c' + 1, 'n' + 1, '/' + 1, 'a'
		+ 1, 'p' + 1, 'i' + 1};

char url_res[128] = { 0 };

//zu4bfqhrpxidybsbh5yflswm2svsdd01
int key[] = { 'z' + 1, 'u' + 1, '4' + 1, 'b' + 1, 'f' + 1, 'q' + 1, 'h' + 1, 'r'
		+ 1, 'p' + 1, 'x' + 1, 'i' + 1, 'd' + 1, 'y' + 1, 'b' + 1, 's' + 1, 'b'
		+ 1, 'h' + 1, '5' + 1, 'y' + 1, 'f' + 1, 'l' + 1, 's' + 1, 'w' + 1, 'm'
		+ 1, '2' + 1, 's' + 1, 'v' + 1, 's' + 1, 'd' + 1, 'd' + 1, '0' + 1, '1'
		+ 1 };

char key_res[128] = { 0 };

jstring native_get_server_url(JNIEnv* pEnv, jobject pThis) {
	if(strlen(url_res) > 0){
		return (*pEnv)->NewStringUTF(pEnv, url_res);
	}

	int i = 0;
	for (i = 0; i < sizeof(url) / sizeof(int); ++i) {
		url_res[i] = url[i] - 1;
	}

	return (*pEnv)->NewStringUTF(pEnv, url_res);
}

jstring native_get_secrect_key(JNIEnv* pEnv, jobject pThis) {
	if(strlen(key_res) > 0){
			return (*pEnv)->NewStringUTF(pEnv, key_res);
		}

		int i = 0;
		for (i = 0; i < sizeof(key) / sizeof(int); ++i) {
			key_res[i] = key[i] - 1;
		}
		return (*pEnv)->NewStringUTF(pEnv, key_res);
}

static JNINativeMethod gMethods[] = {
    {"getServerUrl", "()Ljava/lang/String;", (void*)native_get_server_url},
    {"getSecrectKey", "()Ljava/lang/String;", (void*)native_get_secrect_key}
};

static int registerNativeMethods(JNIEnv* env
        , const char* className
        , JNINativeMethod* gMethods, int numMethods) {
    jclass clazz;
    clazz = (*env)->FindClass(env, className);

    if (clazz == NULL) {
        return JNI_FALSE;
    }

    if ((*env)->RegisterNatives(env, clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }

    return JNI_TRUE;
}

static int registerNatives(JNIEnv* env) {
#ifdef __DEBUG__
	printf("file:%s", __FILE__);
#endif
    const char* kClassName = "com/legame/paysdk/network/utils/NetLib";//指定要注册的类
    return registerNativeMethods(env, kClassName, gMethods,
            sizeof(gMethods) / sizeof(gMethods[0]));
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env = NULL;
    jint result = -1;

    if ((*vm)->GetEnv(vm, (void**) &env, JNI_VERSION_1_4) != JNI_OK) {
        return -1;
    }
    assert(env != NULL);

    if (!registerNatives(env)) {//注册
        return -1;
    }
    //成功
    result = JNI_VERSION_1_4;

    return result;
}
