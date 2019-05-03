import Vue from 'vue'
import Router from 'vue-router'
import LoginForm from '@/components/LoginForm'
import RegisterForm from '@/components/Register'
import Main from '@/components/Main'
import NovelList from '@/components/novellist/NovelList'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Main',
      redirect:'/NovelList',
      component: Main,
      children: [
        {
          path: '/NovelList',
          name: 'NovelList',
          component: NovelList
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
    }
  ]
})
