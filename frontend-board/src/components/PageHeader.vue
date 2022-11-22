<template>
  <header id="navbar">
      <h3>게시판</h3>
      <div class="navbar__menu">
        <ul class="navbar__menu">
          <li class="navbar__menu__item">
            <router-link to="/" @click="ClickedHome" :class="{navbar__menu__item__clicked : clickedHome}">Home</router-link>
          </li>
          <li class="navbar__menu__item">
            <router-link to="/boardList" @click="ClickedBoard" :class="{navbar__menu__item__clicked : clickedBoard}">Board</router-link>
          </li>
          <li class="navbar__menu__item">
            <router-link to="/pageLogin" v-if="!this.$store.getters.getIsAuth" @click="ClickedLogin" :class="{navbar__menu__item__clicked : clickedLogin}">Login</router-link>
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
      clickedHome: true,
      clickedBoard: false,
      clickedLogin: false,
    }
  },
  methods:{
    ClickedHome(){
      this.clickedHome = true;
      this.clickedBoard = false;
      this.clickedLogin = false;
    },
    ClickedBoard(){
      if (!this.$store.getters.getIsAuth){
        this.clickedHome = false;
        this.clickedBoard = false;
        this.clickedLogin = true;
      }
      else {
        this.clickedHome = false;
        this.clickedBoard = true;
        this.clickedLogin = false;
      }
    },
    ClickedLogin(){
      this.clickedHome = false;
      this.clickedBoard = false;
      this.clickedLogin = true;
    },
    fnLogout(){
      localStorage.removeItem('user_token')
      localStorage.removeItem('user_role')
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
  
