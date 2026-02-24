import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.scss'
import { createBrowserRouter, RouterProvider } from 'react-router-dom'
import { Feed } from './features/authentication/feed/pages/Feed'
import { Login } from './features/authentication/login/Login'
import { Signup } from './features/authentication/signup/Signup'
import { AuthenticationContextProvider } from './features/authentication/contexts/AuthenticationContextProvider'



const router = createBrowserRouter([
{
  element: <AuthenticationContextProvider/>,

  children: [
     {
    path: "/",
    element: <Feed/>,
  },
    {
    path: "/login",
    element: <Login/>,
  },
    {
    path: "/signup",
    element: <Signup/>,
  },
    {
    path: "/request-password-reset",
    element: "Request password reset",
  },
    {
    path: "/verify-email",
    element: "Verify Email",
  },

  ]
}
 
])


createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <RouterProvider router={router}/>
    
  </StrictMode>,
)
