package com.reactnativestt

import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.UiThreadUtil
import com.facebook.react.modules.core.DeviceEventManagerModule

class RNMicModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    private var speechRecognizer: SpeechRecognizer? = null

    override fun getName(): String = "SpeechToText"

    private fun sendEvent(eventName: String, data: String) {
        reactApplicationContext
            .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
            .emit(eventName, data)
    }

    @ReactMethod
    fun startListening() {
        UiThreadUtil.runOnUiThread {
            if (speechRecognizer == null) {
                speechRecognizer = SpeechRecognizer.createSpeechRecognizer(reactApplicationContext)
            }

            val speechIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
                putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
                putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
            }

            speechRecognizer?.setRecognitionListener(object : RecognitionListener {
                override fun onResults(results: Bundle?) {
                    val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    val text = matches?.getOrNull(0) ?: "No speech recognized"
                    sendEvent("onSpeechResult", text)
                }

                override fun onError(error: Int) {
                    sendEvent("onSpeechError", "Error code: $error")
                }

                override fun onReadyForSpeech(params: Bundle?) {}
                override fun onBeginningOfSpeech() {}
                override fun onRmsChanged(rmsdB: Float) {}
                override fun onBufferReceived(buffer: ByteArray?) {}
                override fun onEndOfSpeech() {}
                override fun onPartialResults(partialResults: Bundle?) {}
                override fun onEvent(eventType: Int, params: Bundle?) {}
            })

            speechRecognizer?.startListening(speechIntent)
        }
    }

    @ReactMethod
    fun stopListening() {
        UiThreadUtil.runOnUiThread {
            speechRecognizer?.stopListening()
        }
    }

    @ReactMethod
    fun destroyRecognizer() {
        UiThreadUtil.runOnUiThread {
            speechRecognizer?.destroy()
            speechRecognizer = null
        }
    }

    // Required for RN EventEmitter
    @ReactMethod
    fun addListener(eventName: String) {}

    @ReactMethod
    fun removeListeners(count: Int) {}
}
