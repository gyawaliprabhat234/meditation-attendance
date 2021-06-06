import logo from './logo.svg';
import './App.css';
import React from 'react';
import BarcodeScannerComponent from "react-webcam-barcode-scanner";

function App() {
  const [ data, setData ] = React.useState('Not Found');

  return (
    <div className="App">
      <header className="App-header">
        <h1>CS544 - Meditation Attendance Checking</h1>
        <BarcodeScannerComponent
                width={500}
                height={500}
                onUpdate={(err, result) => {
                  if (result) setData(result.text)
                  else setData('Not Found')
                }}
              />
              <p>{data}</p>
      </header>
    </div>
  );
}

export default App;
