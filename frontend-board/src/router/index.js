import { createRouter, createWebHistory } from 'vue-router';
import PageHome from "@/views/PageHome.vue";
import PageAbout from '@/views/PageAbout.vue';
import BoardList from '@/views/BoardList.vue';
import BoardInsert from '@/views/board/BoardInsert.vue';
import BoardDetail from '@/views/board/BoardDetail.vue';
import PageLogin from '@/views/common/PageLogin.vue';
// import {store} from "@/vuex/store";

// 컴포넌트 진입 전을 제어할 수 있는 beforeEnter 가드를 추가하고 권한을 확인하는 함수를 적용
const requireAuth = () => (from, to, next) => {
  const token = localStorage.getItem('user_token')
  if (token) {
    // store.state.isLogin = true
    return next()
  } // isLogin === true면 페이지 이동
  next('/pageLogin') // isLogin === false면 다시 로그인 화면으로 이동
}

const routes = [
  {
    path: "/",
    name: "PageHome",
    component: PageHome,
  },
  {
    path: "/pageLogin",
    name: "PageLogin",
    component: PageLogin,
  },
  {
    path: "/pageAbout",
    name: "PageAbout",
    component: PageAbout,
  },
  {
    path: "/boardList",
    name: "BoardList",
    component: BoardList,
    beforeEnter: requireAuth()
  },
  {
    path: "/boardInsert",
    name: "BoardInsert",
    component: BoardInsert,
    beforeEnter: requireAuth()
  },
  {
    path: "/boardDetail",
    name: "BoardDetail",
    component: BoardDetail,
    beforeEnter: requireAuth()
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
  linkActiveClass: "route-link-active",
  linkExactActiveClass: "route-link-active"
});

export default router;
