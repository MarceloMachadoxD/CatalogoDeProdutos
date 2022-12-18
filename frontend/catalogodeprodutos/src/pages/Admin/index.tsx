import './styles.css';
import Navbar from './Navbar';
import { Outlet } from 'react-router-dom';

function Admin() {
  return (
    <>
      <div className="admin-container">
        <Navbar />
        <div className="admin-content">
          <Outlet />
        </div>
      </div>
    </>
  );
}

export default Admin;
