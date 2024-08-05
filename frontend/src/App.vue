<script setup>
import { RouterLink, RouterView } from 'vue-router'
import axios from "axios"
const response = await axios.get("/api/user/henry/")
console.log(response)
</script>

<template>
  <header>
    <img alt="Vue logo" class="logo" src="@/assets/logo.svg" width="125" height="125" />

    <div class="wrapper">
      <HelloWorld msg="You did it!" />

      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>

  <RouterView />
</template>


<style scoped lang="scss">

</style>
