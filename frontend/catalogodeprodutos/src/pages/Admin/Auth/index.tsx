import { ReactComponent as AuthImage } from 'assets/images/auth-image.svg';
import { Route, Switch } from 'react-router-dom';

import './styles.css';
import Login from './Login';

const Auth = () => {
  return (
    <div className="auth-container">
      <div className="auth-banner-container">
        <h1>Divulgue seus produtos no DSCatalog</h1>
        <p>Faça parte do nosso catálogo de divulgação</p>
        <AuthImage />
      </div>
      <div className="auth-form-container">
        <Switch>
          <Route path="/admin/auth/login" exact>
            <Login />
          </Route>
          <Route path="/admin/auth/singup" exact>
            <h1>SINGUP</h1>
          </Route>
          <Route path="/admin/auth/recover" exact>
            <h1>RECOVER</h1>
          </Route>
        </Switch>
      </div>
    </div>
  );
};

export default Auth;
