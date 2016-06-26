LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := PaySDK
LOCAL_SRC_FILES := netlib.c

include $(BUILD_SHARED_LIBRARY)
