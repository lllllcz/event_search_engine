import "antd/dist/antd.css";
import './App.css';
import { BrowserRouter, Routes, Route} from "react-router-dom"

import MainPage from "./view/main";
import LoginPage from "./view/login";
import RegisterView from "./view/RegisterView";
import CollectionView from "./view/CollectionView";
import HistoryView from "./view/HistoryView";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginPage/>}></Route>
          <Route path="/MainPage" element={<MainPage/>}></Route>
          <Route path="/RegisterView" element={<RegisterView/>}></Route>
          <Route path="/CollectionView" element={<CollectionView/>}></Route>
          <Route path="/HistoryView" element={<HistoryView/>}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
