module.exports = {
  dependencies: {
    "react-native-speech-convertor": {
      root: __dirname,
      platforms: {
        android: {
          sourceDir: "./android",
          packageImportPath: "import com.reactnativestt;",
          packageInstance: "new RNMicPackage()",
        },
      },
    },
  },
};
