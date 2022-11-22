<template>
  <header id="navbar">
      <h3>게시판</h3>
      <div class="navbar__menu">
        <ul class="navbar__menu">
          <li class="navbar__menu__item">
            <router-link to="/">Home</router-link>
          </li>
          <li class="navbar__menu__item">
            <router-link to="/boardList">Board</router-link>
          </li>
          <li class="navbar__menu__item">
            <router-link to="/pageLogin" v-if="!this.$store.getters.getIsAuth">Login</router-link>
            <a v-if="this.$store.getters.getIsAuth" @click="fnLogout">Logout</a>
          </li>
        </ul>
    </div> 
  </header>
</template>

<script>

export default {
  data(){
    return{

    }
  },
  methods:{
    fnLogout(){
      localStorage.removeItem('user_token')
      localStorage.removeItem('user_role')
      localStorage.removeItem('vuex')
      location.reload() //새로고침으로 화면 깜빡임 수정하기
    }
  }
}
</script>
<style scoped>
  a{
    color: #ffffff;
  }
  h3 {
    margin-left: 10px;
  }
</style>
  
