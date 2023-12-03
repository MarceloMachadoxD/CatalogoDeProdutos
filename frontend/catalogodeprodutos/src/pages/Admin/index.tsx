import { Switch } from 'react-router-dom';
import Navbar from './Navbar';

import './styles.css';
import Users from './User';
import PrivateRoute from 'components/PrivateRoute';
import { hasAnyRoles } from 'util/requests';

const Admin = () => {
  const isAdmin = hasAnyRoles(['ROLE_ADMIN']);

  return (
    <div className="admin-container">
      <Navbar />
      <div className="admin-content">
        <Switch>
          <PrivateRoute path="/admin/products">
            <h1>Product CRUD</h1>
          </PrivateRoute>
          <PrivateRoute path="/admin/categories">
            <h1>Category CRUD</h1>
          </PrivateRoute>
          {isAdmin && (
            <PrivateRoute roles={['ROLE_ADMIN']} path="/admin/users">
              <Users />
            </PrivateRoute>
          )}
        </Switch>
      </div>
    </div>
  );
};

export default Admin;
