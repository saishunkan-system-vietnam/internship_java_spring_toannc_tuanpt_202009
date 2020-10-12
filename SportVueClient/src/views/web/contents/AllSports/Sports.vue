<template>
  <v-expansion-panels multiple>
    <v-expansion-panel v-for="(item, i) in tournaments" :key="i">
      <v-expansion-panel-header disable-icon-rotate
        ><template v-if="item.type === 'Football'"
          ><v-icon style="max-width: 30px" medium color="green darken-2">
            mdi-soccer
          </v-icon></template
        >
        <template v-else-if="item.type === 'BaskestBall'"
          ><v-icon style="max-width: 30px" medium color="green darken-2">
            sports_basketball
          </v-icon></template
        >
        <template v-else-if="item.type === 'TableTennis'"
          ><v-icon style="max-width: 30px" medium color="green darken-2">
            sports_tennis
          </v-icon></template
        >
        {{ item.type }}
      </v-expansion-panel-header>

      <v-expansion-panel-content>
        <v-expansion-panels inset>
          <v-expansion-panel
            v-for="(tournament, t) in item.tournament"
            :key="t"
          >
            <v-expansion-panel-header disable-icon-rotate>
              <router-link to="/"></router-link>
              <a
                v-b-popover.hover.left="tournament.nameTour"
                :href="
                  $router.resolve({
                    path: '/DetailTournametSoccer/' + item.idTour,
                  }).href
                "
                style="color: black; margin-left: -30px"
              >
                {{
                  tournament.nameTour.length < 10
                    ? tournament.nameTour
                    : tournament.nameTour.slice(0, 10) + "..."
                }}</a
              >
            </v-expansion-panel-header>
            <v-expansion-panel-content
              v-for="(schedule, s) in tournament.schedule"
              :key="s"
            >
              {{ schedule }}
            </v-expansion-panel-content>
          </v-expansion-panel>
        </v-expansion-panels>
      </v-expansion-panel-content>
    </v-expansion-panel>
  </v-expansion-panels>
</template>
<script>
export default {
  props: {
    tournaments: Array,
  },
  data: {
    disabled: false,
  },
  mounted() {
    console.log(this.tournaments);
  },
  methods: {},
};
</script>
<style>
</style>