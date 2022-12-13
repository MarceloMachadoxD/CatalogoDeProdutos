import Home from 'pages/Home';
import Admin from 'pages/Admin';
import Navbar from 'components/Navbar';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Catalog from 'pages/Catalog';
import ProductDetails from 'pages/ProductDetails';

const Router = () => {
  return (
    <>
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/products" element={<Catalog />} />
          <Route path="/products/:productId" element={<ProductDetails />} />
          <Route path="/admin" element={<Admin />} />
        </Routes>
      </BrowserRouter>
    </>
  );
};

export default Router;
