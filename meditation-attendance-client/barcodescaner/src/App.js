import logo from './logo.svg';
import './App.css';
import React from 'react';
import BarcodeScannerComponent from "react-webcam-barcode-scanner";

function App() {
    const [data, setData] = React.useState('');
    const [scanning, setScanning] = React.useState(false);
    const [scanningSuccess, setScanningSuccess] = React.useState(false);
    const [scanningFailed, setScanningFailed] = React.useState(false);

    const handleBarcodeResult = (err, result) => {
        if (result) {
            const barcode = result.text;
            if ((barcode !== null || barcode !== 'undefined') && !scanning)
                setScanning(true);
            setData(barcode);
            console.log("Barcode " + barcode + " scanning " + true);
            fetch(`/api/barcode?barCodeId=${barcode}`)
                .then(function (response) {
                    if (!response.ok) {
                        throw Error(response.statusText);
                    }
                    return response;
                })
                .then(response => response)
                .then(handleScanningUpdate).catch(handleScanningFailed);
            console.log("Barcode " + barcode + " scanning " + true);

        }
    };

    const sleep = (milliseconds) => {
        return new Promise(resolve => setTimeout(resolve, milliseconds))
    }

    const handleScanningFailed = () => {
        setScanningFailed(true);
        setScanningSuccess(false);
        resetReader();
    }

    const handleScanningUpdate = () => {
        setScanningSuccess(true);
        setScanningFailed(false);
        resetReader();

    }

    const resetReader = () => {
        sleep(2000).then(r => {
            setScanningFailed(false);
            setScanningSuccess(false);
            setScanning(false);
            setData("");
        });
    }

    return (
        <div className="App">
            <header className="App-header">
                <h1>CS544 - Meditation Attendance Checking</h1>
                {!scanning && <BarcodeScannerComponent
                    width={500}
                    height={500}
                    onUpdate={handleBarcodeResult}
                />}
                {scanning && !scanningSuccess && !scanningFailed &&
                    <div width={500}
                        height={500}>
                        <h1>SCANNING....</h1>
                        <p>{data}</p>
                    </div>
                }
                {scanningSuccess &&
                    <div width={500} height={500}>
                        <h1>SCANNING SUCCESS</h1>
                        <p>{data}</p>
                    </div>
                }
                {scanningFailed &&
                    <div width={500} height={500}>
                        <h1>SCANNING FAILED</h1>
                        <p>{data}</p>
                    </div>
                }
            </header>
        </div>
    );
}

export default App;
