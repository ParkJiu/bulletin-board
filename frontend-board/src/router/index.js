import { createRouter, createWebHistory } from 'vue-router';
import PageHome from "@/views/PageHome.vue";
import PageAbout from '@/views/PageAbout.vue';
import BoardList from '@/views/BoardList.vue';
import BoardInsert from '@/views/board/BoardInsert.vue'
import BoardDetail from '@/views/board/BoardDetail.vue'

const routes = [
  {
    path: "/",
    name: "PageHome",
    component: PageHome,
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
  },
  {
    path: "/boardInsert",
    name: "BoardInsert",
    component: BoardInsert,
  },
  {
    path: "/boardDetail",
    name: "BoardDetail",
    component: BoardDetail,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
