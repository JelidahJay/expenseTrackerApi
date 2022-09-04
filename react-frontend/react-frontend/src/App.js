import HeaderComponent from './components/HeaderComponent';
import Categories from './components/pages/Categories';
import Expenses from './components/pages/Expenses';
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom';

function App() {
  return (
    <div>
      <Router>
         <HeaderComponent />
          <div className="container">
              <Routes>
                      <Route path = "/" element = {<Categories/>}></Route>
                      <Route path = "/expenses" element = {<Expenses/>}></Route>                   
              </Routes>
          </div>
      </Router>
    </div>
  );
}

export default App;