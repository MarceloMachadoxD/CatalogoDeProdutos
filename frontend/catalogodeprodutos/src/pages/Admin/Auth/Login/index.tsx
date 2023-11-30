import { useState } from 'react';
import { Link } from 'react-router-dom';
import ButtonIcon from 'components/ButtonIcon';
import { useForm } from 'react-hook-form';
import { requestBackendLogin } from 'util/requests';

import './styles.css';

type FormData = {
  username: string;
  password: string;
};

const Login = () => {
  const [errorMessage, setErrorMessage] = useState<string>();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>();
  const [isError, setIsError] = useState(false);

  const onSubmit = (formData: FormData) => {
    requestBackendLogin(formData)
      .then((response) => {
        console.log('Sucesso: ', response.data);
        setIsError(false);
      })
      .catch((error) => {
        setErrorMessage(error.response.data.error_description);
        setIsError(true);
        console.log('Erro: ', error.response.data.error_description);
      });
  };
  return (
    <div className="base-card login-card">
      <h1>LOGIN</h1>
      {isError && (
        <div className="alert alert-danger">
          <p className="mb-0">{errorMessage}</p>
        </div>
      )}
      <form onSubmit={handleSubmit(onSubmit)}>
        <div className="mb-4">
          <input
            {...register('username', { required: 'Campo Obrigatório' })}
            type="text"
            className="form-control base-input"
            placeholder="Email"
            name="username"
          />
          <div className="invalid-feedback d-block">
            {errors.username?.message}
          </div>
        </div>
        <div className="mb-2">
          <input
            {...register('password', { required: 'Campo Obrigatório' })}
            type="password"
            className="form-control base-input "
            placeholder="Password"
            name="password"
          />
          <div className="invalid-feedback d-block">
            {errors.password?.message}
          </div>
        </div>
        <Link to="/admin/auth/recover" className="login-link-recover">
          Esqueci a senha
        </Link>
        <div className="login-submit">
          <ButtonIcon text="Fazer login" />
        </div>
        <div className="signup-container">
          <span className="not-registered">Não tem Cadastro?</span>
          <Link to="/admin/auth/register" className="login-link-register">
            CADASTRAR
          </Link>
        </div>
      </form>
    </div>
  );
};

export default Login;
