import { createRouter, createWebHistory } from 'vue-router';
import PageHome from "@/views/PageHome.vue";
import PageAbout from '@/views/PageAbout.vue';
import BoardList from '@/views/BoardList.vue';

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
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
