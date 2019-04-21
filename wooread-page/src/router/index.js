import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import LoginForm from '@/components/LoginForm'
import Fuck from '@/components/Fuck'

import RegisterForm from '@/components/Register'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
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
