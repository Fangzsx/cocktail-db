#include <jni.h>



JNIEXPORT jstring JNICALL
Java_com_fangzsx_retrofit_1room_viewmodels_CocktailActivityViewModel_getApi(JNIEnv *env,jobject thiz) {
    return (*env)-> NewStringUTF(env, "AIzaSyASsMuDtirwhcrNvV8syrKlxSm-Wo4K480");
}