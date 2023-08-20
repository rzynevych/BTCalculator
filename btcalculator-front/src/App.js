import React from 'react';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Link,
  Navigate,
} from 'react-router-dom';
import s from './App.module.css';
import EntryPage from './pages/EntryPage';
import AdminPage from './pages/AdminPage';
import UserPage from './pages/UserPage';

function App() {
  
    return (
      
      <div className={s.main_container}>
        <Router>
          <Routes>
            <Route path='/' element={<EntryPage/>}/>
            <Route path='/adminPage' element={<AdminPage/>}/>
            <Route path='/userPage' element={<UserPage/>}/>
          </Routes>
        </Router>
      </div>
      
    );
}

export default App;
