import Vue from 'vue'
import Router from 'vue-router'
import LoginForm from '@/components/LoginForm'
import RegisterForm from '@/components/Register'
import Main from '../components/Main'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      component: Main
    },
    {
      path: '/LoginForm',
      name: 'LoginForm',
      component: LoginForm
    },
    {
      path: '/RegisterForm',
      name: 'RegisterForm',
      component: RegisterForm
    }
  ]
})
