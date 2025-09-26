# React Native Speech Convertor

A React Native library for converting speech to text in real time.  
Easily integrate voice recognition into your mobile app with a simple API and event-driven architecture. Supports real-time transcription, error handling, and is optimized for lightweight usage. Currently available for Android with English language support; future updates will include iOS and more languages.

> **Note:** Currently supports **Android** only. **iOS** support is planned for future releases.

> **Language Support:** Only **English** is supported at this time. Support for additional languages is planned for future releases.

---

## Features

- Converts speech to text instantly
- Easy to use API
- Listen for results and errors
- Lightweight and simple to add
- Android offline support

---

## Installation

Install the library using npm:

```bash
npm install react-native-speech-convertor
```

---

## Usage

### Import the Library

```js
import { micEvents, SpeechToText } from 'react-native-speech-convertor';
```

### Set Up State and Event Listeners

Use React's `useEffect` to set up listeners for speech results and errors:

```js
useEffect(() => {
    // Listen for recognized speech results
    const resultListener = micEvents.addListener('onSpeechResult', (text: string) => {
        console.log('Recognized text:', text);
        setText(text); // Update your state with the recognized text
    });

    // Listen for speech recognition errors
    const errorListener = micEvents.addListener('onSpeechError', (err: any) => {
        console.error('Speech error:', err);
    });

    // Clean up listeners on unmount
    return () => {
        resultListener.remove();
        errorListener.remove();
    };
}, []);
```

### Start Listening

Begin speech recognition by calling:

```js
SpeechToText.startListening();
```

### Stop Listening

Manually stop speech recognition if needed:

```js
SpeechToText.stopListening();
```

> On Android, listening will automatically stop when speech ends. Use `stopListening()` to manually end recognition if required.

---

## API Reference

### `SpeechToText.startListening()`

Starts the speech recognition process.

### `SpeechToText.stopListening()`

Stops the speech recognition process.

### `micEvents.addListener(event, callback)`

Adds an event listener for speech events.

- `onSpeechResult`: Triggered when speech is recognized.
- `onSpeechError`: Triggered when an error occurs.

---

## Example

```js
import React, { useEffect, useState } from 'react';
import { View, Text, Button } from 'react-native';
import { micEvents, SpeechToText } from 'react-native-speech-convertor';

const SpeechDemo = () => {
    const [text, setText] = useState('');

    useEffect(() => {
        const resultListener = micEvents.addListener('onSpeechResult', setText);
        const errorListener = micEvents.addListener('onSpeechError', console.error);

        return () => {
            resultListener.remove();
            errorListener.remove();
        };
    }, []);

    return (
        <View>
            <Button title="Start Listening" onPress={SpeechToText.startListening} />
            <Button title="Stop Listening" onPress={SpeechToText.stopListening} />
            <Text>Recognized Text: {text}</Text>
        </View>
    );
};

export default SpeechDemo;
```

---

## Troubleshooting

- Ensure microphone permissions are granted in your AndroidManifest.xml.
- If speech recognition does not start, check device compatibility and permissions.

---

## Roadmap

- [ ] iOS support
- [ ] Custom language selection
- [ ] Improved error handling

---

## License

MIT

---

## Contributing

Contributions are welcome! Please open issues or submit pull requests for improvements and new features.

Thank you!

