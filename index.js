import { NativeEventEmitter, NativeModules } from 'react-native';

const { SpeechToText } = NativeModules;
const micEvents = new NativeEventEmitter(SpeechToText);

export { SpeechToText, micEvents };
