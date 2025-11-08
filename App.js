import { NativeModules } from 'react-native';
const { ZoomModule } = NativeModules;

ZoomModule.initializeZoom('TU_ZOOM_SDK_KEY', 'TU_ZOOM_SDK_SECRET')
  .then(msg => console.log(msg))
  .catch(err => console.error(err));