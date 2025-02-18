import './app.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginPage from './component/LoginPage';
import Welcome from './component/Welcome';
import RegisterPage from './component/RegisterPage';
import Home from './component/Home';
import ItemUpload from './component/ItemUpload';
import ItemList from './component/ItemList';
import AddClothingPost from './component/AddClothingPost';
import CommunityPage from './component/CommunityPage';
import MyContacts from './component/MyContacts';
import RecommendationComponent from './component/RecommendationComponent.jsx';
import ItemDetail from './component/ItemDetail';
import PrivateRoute from './component/PrivateRoute'; // Import your PrivateRoute component

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Welcome />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />

        {/* Protecting all specified routes with PrivateRoute */}
        <Route path="/home" element={
          <PrivateRoute>
            <Home />
          </PrivateRoute>
        } />
        <Route path="/itemadd" element={
          <PrivateRoute>
            <ItemUpload />
          </PrivateRoute>
        } />
        <Route path="/itemdisplay" element={
          <PrivateRoute>
            <ItemList />
          </PrivateRoute>
        } />
        <Route path="/postdisplay" element={
          <PrivateRoute>
            <CommunityPage />
          </PrivateRoute>
        } />
        <Route path="/postadd" element={
          <PrivateRoute>
            <AddClothingPost />
          </PrivateRoute>
        } />
        <Route path="/contact" element={
          <PrivateRoute>
            <MyContacts />
          </PrivateRoute>
        } />
        <Route path="/recommend" element={
          <PrivateRoute>
            <RecommendationComponent />
          </PrivateRoute>
        } />
        <Route path="/track" element={
          <PrivateRoute>
            <ItemDetail />
          </PrivateRoute>
        } />
      </Routes>
    </Router>
  );
}

export default App;
