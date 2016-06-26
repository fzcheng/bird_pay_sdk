/*
 * netlib.h
 *
 *  Created on: 2013-6-8
 *      Author: terry
 */
#include <jni.h>

#ifndef NETLIB_H_
#define NETLIB_H_
//#define __DEBUG__

#if defined(__cplusplus)
extern "C" {
#endif

jstring native_get_server_url(JNIEnv *, jclass);
jstring native_get_secrect_key(JNIEnv *, jclass);

#if defined(__cplusplus)
}
#endif

#endif /* NETLIB_H_ */
