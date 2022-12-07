import Home from 'pages/Home';
import Admin from 'pages/Admin';
import Navbar from 'components/Navbar';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Catalog from 'pages/Catalog';

const Router = () => {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/products" element={<Catalog />} />
          <Route path="/admin" element={<Admin />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default Router;
