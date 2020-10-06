<template>
  <div>
    <router-view />
  </div>
</template>
<script>
export default {
  data() {
    return {
      show: false
    };
  },
  watch: {
    $route(route) {
      if (route.name == "User") {
        this.show = false;
      } else {
        this.show = true;
      }
    }
  }
};
</script>