<template>
  <header id="navbar">
      <h3>게시판</h3>
      <div class="navbar__menu">
        <ul class="navbar__menu">
          <li class="navbar__menu__item">
            <router-link to="/" @click="ClickedHome" :class="{clicked : clickedHome}">Home</router-link>
          </li>
          <li class="navbar__menu__item">
            <router-link to="/boardList" @click="ClickedBoard" :class="{clicked : clickedBoard}">Board</router-link>
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
    }
  },
  methods:{
    ClickedHome(){
      this.clickedHome = true;
      this.clickedBoard = false;
    },
    ClickedBoard(){
      this.clickedHome = false;
      this.clickedBoard = true;
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
  
