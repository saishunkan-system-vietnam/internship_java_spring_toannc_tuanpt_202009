<template>
  <div>
    <v-card>
      <v-container>
        <h3> <v-icon medium color="green darken-2"> mdi-soccer </v-icon>TOURANMENT :{{ tournament.nameTour }}</h3>

        <v-tabs v-model="tab">
          <v-tabs-slider></v-tabs-slider>

          <v-tab href="#tab-1"> Summary </v-tab>

          <v-tab href="#tab-2"> Matches </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item :value="'tab-1'">
            <v-card flat>
              <v-card-text>
                <h5 style="color: red">Team</h5>
                <v-simple-table>
                  <template v-slot:default>
                    <thead>
                      <tr>
                        <th class="text-left">Name</th>
                        <th class="text-center">Logo</th>
                        <th class="text-center">Total member</th>
                        <th class="text-left">Description</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="item in tournament.team"
                        :key="item.name"
                        @click="detailTeam(item.idTeam)"
                      >
                        <td>{{ item.nameTeam }}</td>
                        <td class="text-center" >
                          <v-avatar style="margin:5px">
                            <img :src="item.logo" alt="John" />
                          </v-avatar>
                        </td>
                        <td class="text-center">{{ item.profile.length }}</td>
                        <td>{{ item.description }}</td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
                <h5 style="color: red">Rank</h5>
                <v-simple-table>
                  <template v-slot:default>
                    <thead>
                      <tr>
                        <th class="text-left">Rank</th>
                        <th class="text-left">Name</th>
                        <th class="text-left">Win rate</th>
                        <th class="text-left">The most recent matches</th>
                      </tr>
                    </thead>
                    <tbody>
    <tr v-for="(item, index) in rank" :key="index" :style="index==0?'background:#8fbc8f':index==1?'background:#ddd':index==rank.length-1?'color:red':''">
                        <td>{{ index + 1 }}</td>

                        <td>{{ item.name }}</td>
                        <td>{{ item.rank != "NaN" ? item.rank * 100 : 0 }}%</td>
                        <td>
                          <div style="padding: 5px">
                            <v-row class="text-center">
                              <v-col style="color:white"
                                cols="12"
                                sm="2"
                                v-for="(schedule, index) in item.list"
                                :key="index"
                                :style="
                                  schedule.video == null
                                    ? ''
                                    : schedule.idwinner == item.idTeam
                                    ? 'background:green;margin:2px'
                                    : 'background:red;margin:2px'
                                "
                              ><div v-if="schedule.video!=null" >
                                <span v-if="schedule.idwinner == item.idTeam">
                                  W
                                </span>
                                <span v-else> L</span>
                                </div>
                              </v-col>
                            </v-row>
                          </div>
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item :value="'tab-2'">
            <v-card flat>
              <v-card-text>
                
                <v-simple-table>
                  <template v-slot:default>
                    <thead>
                      <tr>
                        <th>Status
                        </th>
                         <th>Time
                        </th>
                         <th>Team 1
                        </th>
                         <th>Score
                        </th>
                         <th>Team 2
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr
                        v-for="(item, index) in schedule"
                        color="primary"
                        :key="index"
                        v-b-popover.hover.bottom="'Kích vào để xem chi tiết'"
                        @click="detail(item)"
                      >
                        <td
                          :style="
                            item.status == 0
                              ? 'color:red'
                              : item.status == 1
                              ? 'color:blue'
                              : 'color:#a99893'
                          "
                        >
                          {{
                            item.status == 0
                              ? "UPCOMMING"
                              : item.status == 1
                              ? "ON GAME"
                              : "FINSISHED"
                          }}
                        </td>
                        <td>
                            <div v-if="item.status==2">
                              {{item.timeEnd}}
                            </div>
                            <div v-else>
                              {{item.timeStart}}
                            </div>
                        </td>
                        <td>
                          {{ !!schedule ? item.team[0].nameTeam : "" }}
                        </td>
                        <td>
                          {{ item.status == 2 &&item.video!=null ? item.scoreTeam1 : "?" }}
                          -
                          {{ item.status == 2 &&item.video!=null ? item.scoreTeam2 : "?" }}
                        </td>
                        <td>
                          {{ !!schedule ? item.team[1].nameTeam : "" }}
                        </td>
                      </tr>
                    </tbody>
                  </template>
                </v-simple-table>
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-container>
    </v-card>
  </div>
</template>
<script>
export default {
  data() {
    return {
      rank: "",
      tournament: "",
      tab: null,
      open: false,
      schedule:''
    };
    
  },
  created() {
    this.gettournament();
    console.log(this.$route)
   
  },
  methods: {
    detailTeam(id) {
      this.$router.push("/DetailTeamSoccer/" + id);
    },
    gettournament() {
      this.$store
        .dispatch("tournament/getById", this.$route.params.id)
        .then((response) => {
          this.tournament = response.data;
        this.$store.dispatch("schedule/getByIdTour",this.tournament.idTour).then(response=>{
          this.schedule=response.data;
        })
        });
      this.$store
        .dispatch("tournament/getRankByTour", this.$route.params.id)
        .then((response) => {
          this.rank = response.data;
        });
    },
    detail(tournament) {
      this.detailScore = tournament;
      this.open = true;
      var myWindow = window.open(
        "http://localhost:8080/soccer/detail/" + tournament.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
    },
  },
};
</script>