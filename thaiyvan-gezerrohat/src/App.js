import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Header from './Components/Header';
import Footer from './Components/Footer';
import Register from './Components/Register';
import Login from './Components/Login';
import EventList from './Components/EventList';
import EventDetails from './Components/EventDetails';
import CreateEvent from './Components/CreateEvent';

function App() {
    return (
        <Router>
            <div className="App">
                <Header />
                <div className="content">
                    <Routes>
                        <Route path="/" element={<EventList />} />
                        <Route path="/register" element={<Register />} />
                        <Route path="/login" element={<Login />} />
                        <Route path="/event/:id" element={<EventDetails />} />
                        <Route path="/create-event" element={<CreateEvent />} />
                    </Routes>
                </div>
                <Footer />
            </div>
        </Router>
    );
}

export default App;
